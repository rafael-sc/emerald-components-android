package stone.com.br.basiccomponents.common

/**
 * Created on 04/06/2018
 *
 * @author Victor Cruz
 * @email victor.cruz@stone.com.br
 */
object UtilValidator {

    private val EMAIL_REGEX = Regex("[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]"
            + "+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")

    fun isEmailValid(email: String) =
            email.matches(EMAIL_REGEX)
}