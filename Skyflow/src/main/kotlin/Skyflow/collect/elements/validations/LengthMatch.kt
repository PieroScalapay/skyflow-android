package  com.Skyflow.collect.elements.validations

/**
Validate input in scope of length.
 */
class LengthMatch(val minLength: Int, val maxLength: Int, override var error: SkyflowValidationError = "") : SkyflowValidationProtocol() {


    /// validate length of text
      override fun validate(text: String?) : Boolean {

        if(text!!.isEmpty())
        {
            return true
        }
        return text.length >= minLength && text.length <= maxLength
    }


}