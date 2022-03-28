//
//  CreatorView.swift
//  ScalesApp
//
//  Created by Seth Climenhaga on 2022-03-20.
//

import SwiftUI

struct CreatorView: View {
    
    @ObservedObject var viewModel: CreatorVM = CreatorVM()
    
    var body: some View {
       Text("Hello")
    }
}

struct CreatorView_Previews: PreviewProvider {
    static var previews: some View {
        CreatorView()
    }
}
