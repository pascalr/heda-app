package com.heda.models

import com.heda.helpers.searchScore

class SearchResult<T>(obj: T, scoringFunction: (T) -> Double) {
    var score: Double
    var result: T

    init {
        result = obj
        score = scoringFunction(obj)
    }
}