import com.delivery.domain.deliveryfeecalculator.OrderDataNotValidException
import com.delivery.domain.deliveryfeecalculator.dto.OrderDataDto
import java.math.BigInteger
import java.time.ZonedDateTime

internal object OrderDataDtoValidator {
    private const val NULL_OR_NEGATIVE_MESSAGE = " must be not null"

    internal fun validate(orderDataDto: OrderDataDto) {
        val errorMessages = mutableListOf<String>()

        validateNonNegative(orderDataDto.cartValue, "cart value", errorMessages)
        validateNonNegative(orderDataDto.deliveryDistance, "delivery distance", errorMessages)
        validateNonNegative(orderDataDto.numberOfItems, "number of items", errorMessages)
        validateNotNull(orderDataDto.orderTime, errorMessages)

        if (errorMessages.isNotEmpty()) {
            throw OrderDataNotValidException(errorMessages)
        }
    }

    private fun validateNonNegative(value: BigInteger?, fieldName: String, errorMessages: MutableList<String>) {
        if (value == null || value <= BigInteger.ZERO) {
            errorMessages.add(fieldName + NULL_OR_NEGATIVE_MESSAGE)
        }
    }

    private fun validateNotNull(dateTime: ZonedDateTime?, errorMessages: MutableList<String>) {
        if (dateTime == null) {
            errorMessages.add("order time must be not null")
        }
    }
}