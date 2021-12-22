import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
	let greet = Greeting().greeting()

	var body: some View {
        VStack {
            Text(UUIDKt.randomUUID())
            Button("api call") {
                self.viewModel.fetchEvents()
            }
        }
	}
}

extension ContentView {
    class ViewModel: ObservableObject {
        let api: ConnpassApi
        
        init(api: ConnpassApi) {
            self.api = api
        }
        
        func fetchEvents() {
            api.events(completionHandler: { (eventsResponse, error) in
                if let events = eventsResponse?.events {
                    print(events)
                } else {
                    if let error = error {
                        print(error)
                    }
                }
            })
        }
    }
}
