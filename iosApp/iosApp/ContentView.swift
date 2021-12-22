import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
	let greet = Greeting().greeting()

	var body: some View {
        VStack {
            Text(self.viewModel.events.count.description)
            Text(UUIDKt.randomUUID())
            Button("api call") {
                self.viewModel.fetchEvents()
            }
        }
	}
}

extension ContentView {
    class ViewModel: ObservableObject {
        let repository: EventRepository
        
        @Published var events: [EventEntity] = []
        
        init(repository: EventRepository) {
            self.repository = repository
            repository.events.collect(collector: Collector<[EventEntity]> { events in
                self.events = events
            }) { (unit, error) in
               print("flow finished")
            }
        }
        
        func fetchEvents() {
            repository.refresh { (_, error) in
                if let error = error {
                    print("error occured" + error.localizedDescription)
                }
            }
        }
    }
}

class Collector<T>: Kotlinx_coroutines_coreFlowCollector {

    let callback:(T) -> Void

    init(callback: @escaping (T) -> Void) {
        self.callback = callback
    }

    func emit(value: Any?, completionHandler: @escaping (KotlinUnit?, Error?) -> Void) {
        callback(value as! T)
        completionHandler(KotlinUnit(), nil)
    }
}
