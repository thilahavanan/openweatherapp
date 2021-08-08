package com.mobiquity.test.network.basenetwork

import java.io.IOException

class OfflineExeception(message: String = "Offline") : IOException(message) {
}