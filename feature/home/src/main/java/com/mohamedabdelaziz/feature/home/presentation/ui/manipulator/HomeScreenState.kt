package com.mohamedabdelaziz.feature.home.presentation.ui.manipulator

import com.mohamedabdelaziz.feature.home.presentation.data.UIHomeSectionsResponse
import com.mohamedabdelaziz.feature.home.presentation.data.UISearchSectionsResponse

/**
 * @author: Mohamed Abdelaziz
 * @since Sat, 14 Jun 2025
 */
data class HomeScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val uiHomeSectionsResponse: UIHomeSectionsResponse? = null,
    val uiSearchSectionsResponse: UISearchSectionsResponse?= null


    )