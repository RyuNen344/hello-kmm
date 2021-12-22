import SwiftUI
import shared

@main
struct iOSApp: App {
    private let api = HttpClientEngineProviderImplKt.provideApi()
    
    private let database = DatabaseProviderKt.provideDatabase(sqlDriverProvider: SqlDriverProviderImpl())
    
	var body: some Scene {
		WindowGroup {
            ContentView(viewModel: .init(api: api, database: database))
		}
	}
}
