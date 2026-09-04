//
//  AgriOSApp.swift
//  AgriOS
//
//  Created by Ken Murase on 2026/09/02.
//

import SwiftUI
import AgriOSKit

@main
struct AgriOSApp: App {
    @Environment(\.scenePhase)
    private var scenePhase
    
    private let application: Lifecycle = Application.shared
        
    init() { IosMain.shared.bootstrap() }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
        .onChange(of: scenePhase) { _, phase in
            switch phase {
            case .active: application.onStart()
            case .background: application.onStop()
            case.inactive: break
            @unknown default: break
            }
        }
    }
}

#Preview {
    ContentView()
}
