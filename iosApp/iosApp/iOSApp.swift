import SwiftUI
import shared

@main
struct iOSApp: App {
    private let api = HttpClientEngineProviderImplKt.provideApi()
	var body: some Scene {
		WindowGroup {
			ContentView(viewModel: .init(api: api))
		}
	}
}
