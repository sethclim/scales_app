//
//  ScalesAppApp.swift
//  ScalesApp
//
//  Created by Seth Climenhaga on 2022-03-18.
//

import SwiftUI
import kmmsharedmodule

@main
struct ScalesAppApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        KoinKt.doInitKoin()
        return true
    }
}
 
