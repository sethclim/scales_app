//
//  CreatorVM.swift
//  ScalesApp
//
//  Created by Seth Climenhaga on 2022-03-20.
//

import kmmsharedmodule
import Combine

class CreatorVM : CreatorViewModel, ObservableObject {
    @Published var state: CreatorContractState =
    CreatorContractState(scalesCheckBoxes: BasicUiState<KotlinMutableDictionary<ScaleType, KotlinBoolean>>(), techCheckBoxes: BasicUiState<KotlinMutableDictionary<TechType, KotlinBoolean>>(), rootCheckBoxes: BasicUiState<KotlinMutableDictionary<RootType, KotlinBoolean>>(), customRootState: BasicUiState<KotlinBoolean>())
    @Published var isFavorite: Bool = false
    @Published var showAlert: Bool = false
    
    override init() {
        super.init()
        
        collect(flow: uiState, collect: { state in
            self.state = state as! CreatorContractState
        })

        collect(flow: effect) { uiEffect in
            let effect = (uiEffect as! CreatorContractEffect)

            switch effect {
                case CreatorContractEffect.RoutineSaved.shared:
                    self.isFavorite = true
                default:
                    break
            }

            self.showAlert = true
        }
    }
}
