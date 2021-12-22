package com.ryunen344.hello.kmm

import platform.Foundation.NSUUID

actual fun randomUUID() : String = NSUUID().UUIDString()
