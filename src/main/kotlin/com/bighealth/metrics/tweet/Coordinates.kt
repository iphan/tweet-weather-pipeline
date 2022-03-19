package com.bighealth.metrics.tweet

data class Coordinates(val longitude: Double, val latitude: Double) {
    companion object {
        fun parse(coordinates: List<Double>): Coordinates? {
            if (coordinates.size < 2) return null

            if (!validLongitude(coordinates[0]) || !validLatitude(coordinates[1])) return null

            return Coordinates(coordinates[0], coordinates[1])
        }

        private fun validLongitude(longitude: Double): Boolean {
            return longitude >= -180 && longitude <= 180
        }

        private fun validLatitude(latitude: Double): Boolean {
            return latitude >= -90 && latitude <= 90
        }
    }

}