//
//  AgriOSView.swift
//  AgriOS
//
//  Created by Ken Murase on 2026/09/04.
//

import SwiftUI
import AgriOSKit

struct AgriOSViewControllerRepresentable: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        AppleMain.shared.viewController
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        
    }
}
