//
//  ContentView.swift
//  ScalesApp
//
//  Created by Seth Climenhaga on 2022-03-18.
//

import SwiftUI
import kmmsharedmodule

struct ContentView: View {
    var body: some View {
        Text(Greeting().greeting())
             .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
