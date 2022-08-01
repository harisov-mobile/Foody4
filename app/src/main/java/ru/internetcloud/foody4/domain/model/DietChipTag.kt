package ru.internetcloud.foody4.domain.model

enum class DietChipTag(val chipName: String) {
    gluten_free_chip("gluten_free_chip"),
    ketogenic_chip("ketogenic_chip"),
    vegetarian_chip("vegetarian_chip"),
    vegan_chip("vegan_chip"),
    pescetarian_chip("pescetarian_chip"),
    paleo_chip("paleo_chip"),
    primal_chip("primal_chip"),
    whole30_chip("whole30_chip")
}
