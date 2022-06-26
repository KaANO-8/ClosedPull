package com.kaano8.closedpull.api.data

import java.io.IOException

/**
 * Custom exception that's mapped via an interceptor
 */
data class ClosedPrException(val displayMessage: String): IOException()