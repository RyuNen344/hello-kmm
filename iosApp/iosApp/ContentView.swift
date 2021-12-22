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
        
        let database: ConnpassDatabase
        
        let delegate: EventQueriesDelegate
        
        init(api: ConnpassApi, database: ConnpassDatabase) {
            self.api = api
            self.database = database
            self.delegate = EventQueriesDelegate.init(connpassDatabase: database)
        }
        
        func fetchEvents() {
            api.events(completionHandler: { (eventsResponse, error) in
                if let response = eventsResponse {
                    let events = response.toEntity()
                    let seriesRecords = events.filter{ $0.series != nil }.map { $0.series!.toRecord() }
                    let eventRecords = events.map { $0.toRecord() }
                    
                    self.database.transaction(noEnclosing: false) { _ in
                        seriesRecords.forEach {
                            self.delegate.upsertSeries(series: $0)
                        }
                        eventRecords.forEach {
                            self.delegate.upsertEvent(event: $0)
                        }
                    }
                    print(self.delegate.selectAllEvent())
                } else {
                    if let error = error {
                        print(error)
                    }
                }
            })
        }
    }
}
