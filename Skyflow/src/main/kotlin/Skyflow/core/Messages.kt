package Skyflow.core

import Skyflow.utils.Utils
import com.skyflow_android.R

enum class Messages(val message: String) {
    INVALID_URL("Invalid client credentials. Expecting \"https://XYZ\" for vaultURL"),
    INITIALIZE_CLIENT("Initializing skyflow client"),
    CLIENT_INITIALIZED("Initialized skyflow client successfully"),
    CREATE_COLLECT_CONTAINER("Creating Collect container"),
    COLLECT_CONTAINER_CREATED("Created Collect container successfully"),
    CREATE_REVEAL_CONTAINER("Creating Reveal container"),
    REVEAL_CONTAINER_CREATED("Created Reveal container successfully"),
    VALIDATE_RECORDS("Validating insert records"),
    VALIDATE_DETOKENIZE_INPUT("Validating detokenize input"),
    VALIDATE_GET_BY_ID_INPUT("Validating getByID input"),
    VALIDATE_CONNECTION_CONFIG("Validating connection config"),
    VALIDATE_COLLECT_RECORDS("Validating collect element input"),
    VALIDATE_REVEAL_RECORDS("Validating reveal element input"),
    CREATED_COLLECT_ELEMENT("Created collect element %s"),
    CREATED_REVEAL_ELEMENT("Created reveal element %s"),

    RETRIEVING_BEARER_TOKEN("Retrieving bearer token."),
    BEARER_TOKEN_RECEIVED("BearerToken received successfully."),
    RETRIEVING_BEARER_TOKEN_FAILED("Retrieving bearer token failed"),

    ELEMENT_MOUNTED("%s1 Element mounted"),
    ELEMENT_REVEALED("%s1 Element revealed"),
    COLLECT_SUBMIT_SUCCESS("Data has been collected successfully."),
    REVEAL_SUBMIT_SUCCESS("Data has been revealed successfully."),
    INSERT_DATA_SUCCESS("Data has been inserted successfully."),
    DETOKENIZE_SUCCESS("Data has been revealed successfully."),
    GET_BY_ID_SUCCESS("Data has been revealed successfully"),
    BEARER_TOKEN_LISTENER("Listening to GetBearerToken event"),
    BEARER_TOKEN_EMITTER("Emitted GetBearerToken event"),

    INSERT_CALLED("Insert method triggered"),
    INSERTING_RECORDS("Inserting records into vault with id %s"),
    INSERTING_RECORDS_SUCCESS("Successfully inserted records into vault with id %s"),
    INSERTING_RECORDS_FAILED("Failed inserting records into vault with id %s"),
    DETOKENIZE_CALLED("Detokenize method called"),
    DETOKENIZING_RECORDS("Detokenizing records"),
    DETOKENIZING_FAILED("Failed revealed data from vault with id %s"),
    GET_BY_ID_CALLED("getById method called"),
    GETTING_RECORDS_BY_ID_CALLED("retrieving records using skyflow ids"),
    INVOKE_CONNECTION_CALLED("invokeConnection method called"),


    INVALID_VAULT_ID("vault id invalid cannot be found"),
    INVALID_VAULT_URL("vault url %s is invalid or not secure"),
    EMPTY_VAULT_ID("vaultid is empty."),
    EMPTY_VAULT_URL("vault url is empty."),
    INVALID_BEARER_TOKEN("bearer token is invalid or expired"),
    INVALID_TABLE_NAME("Key 'table' doesn't have a value of type String"),
    ELEMENT_EMPTY_TABLE_NAME("element with type %s -  Table key cannot be empty."),
    EMPTY_TABLE_KEY("table key cannot be empty"),
    EMPTY_COLUMN_KEY("column key cannot be empty"),
    RECORDS_KEY_NOT_FOUND("records object is required"),
    ADDITION_RECORDS_KEY_KEY_NOT_FOUND("records object key value not found inside additional Fields"),
    EMPTY_RECORDS("records object cannot be empty"),
    TABLE_KEY_ERROR("key \'table\' is missing or payload is incorrectly formatted"),
    FIELDS_KEY_ERROR("fields key is required"),
    INVALID_COLUMN_NAME("column with given name is not present in the vault"),
    EMPTY_COLUMN_NAME("element with type %s - Column key is required."),
    INVALID_TOKEN_ID("token provided is invalid  "),
    EMPTY_TOKEN_ID("token key cannot be empty "),
    ID_KEY_ERROR("key \'id\' is missing in the payload provided"),
    REDACTION_KEY_ERROR("redaction key is required"),
    INVALID_REDACTION_TYPE("provided redaction type value doesn’t match with one of : \'plain_text\', \'redacted\' ,\'default\' or \'masked\'"),
    INVALID_FIELD("invalid field %s"),
    MISSING_TOKEN("token key is required "),
    MISSING_TOKEN_IN_CONNECTION_REQUEST("element for %s must have token"),
    MISSING_KEY_IDS("ids key is required "),
    EMPTY_RECORD_IDS("ids key cannot be empty"),
    INVALID_RECORD_ID_TYPE("invalid type of records id"),
    MISSING_TABLE_IN_ELEMENT("element with type %s - Table key is required."),
    MISSING_TABLE_KEY("table key is required"),
    INVALID_RECORD_TABLE_VALUE("invalid record table value"),
    INVALID_CONNECTION_URL("connectionURL %s is invalid "),
    INVALID_INPUT("%s"),
    REQUIRED_INPUTS_NOT_PROVIDED("required inputs are not provided"),
    INVALID_EVENT_TYPE("provide a valid event type"),
    INVALID_EVENT_LISTENER("provide valid event listener"),
    UNKNOWN_ERROR("%s"),
    TRANSACTION_ERROR("an error occurred during transaction"),
    CONNECTION_ERROR("error while initializing the connection"),
    MISSING_REDACTION_VALUE("missing redaction value"),
    ELEMENT_NOT_MOUNTED("element for %s is not mounted"),
    DUPLICATE_COLUMN_FOUND("Duplicate element with %s and %s found in container"),
    DUPLICATE_ELEMENT_FOUND("Duplicate Element found in response body"),
    INVALID_RECORDS_TYPE("Key 'records' is of invalid type"),
    INVALID_RECORD_IDS("ids are not valid"),
    MISSING_REDACTION("redaction is missing"),
    EMPTY_KEY_IN_REQUEST_BODY("empty key present in request body"),
    EMPTY_KEY_IN_QUERY_PARAMS("empty key present in query parameters"),
    EMPTY_KEY_IN_PATH_PARAMS("empty key present in path parameters"),
    EMPTY_KEY_IN_REQUEST_HEADER_PARAMS("empty key present in request header"),
    INVALID_FIELD_IN_PATH_PARAMS("invalid data type %s present in path parameters"),
    INVALID_FIELD_IN_QUERY_PARAMS("invalid data type %s present in query parameters"),
    INVALID_FIELD_IN_REQUEST_HEADER_PARAMS("invalid data type %s present in request header"),
    INVALID_FIELD_IN_REQUEST_BODY("invalid data type %s present in request body"),
    FAILED_TO_REVEAL("Failed to reveal"),
    EMPTY_CONNECTION_URL("Empty connection url is passed"),
    NOT_FOUND_IN_RESPONSE("%s is not found in response"),
    BAD_REQUEST("bad request"),
    MISSING_COLUMN("element with type %s - Column key cannot be empty."),
    EMPTY_FIELDS("fields key cannot be empty"),
    SERVER_ERROR("Server error %s"),
    EMPTY_REQUEST_XML("RequestXML is empty"),
    INVALID_REQUEST_XML("Invalid RequestXML in SoapConnection - %s"),
    INVALID_RESPONSE_XML("Invalid ResponseXML in SoapConnection - %s"),
    NOT_FOUND_IN_RESPONSE_XML("Invalid path in responseXML. Element present under %s path is not found in response"),
    AMBIGUOUS_ELEMENT_FOUND_IN_RESPONSE_XML("Ambiguous Element found in responseXML"),
    INVALID_ID_IN_REQUEST_XML("Invalid elementId %s present in RequestXML"),
    EMPTY_ID_IN_REQUEST_XML("empty elementId present in RequestXml"),
    INVALID_ID_IN_RESPONSE_XML("Invalid elementId %s present in ResponseXML"),
    EMPTY_ID_IN_RESPONSE_XML("empty elementId present in ResponseXML"),
    DUPLICATE_ID_IN_RESPONSE_XML("duplicate Id present in ResponseXML"),
    INVALID_FORMAT_REGEX("Invalid formatRegex - no match found for regex: %s"),
    NOT_VALID_TOKENS("following tokens are not valid - %s"),
    NO_TABLE_KEY_IN_UPSERT("\"table\" key is required in upsert options object at index %s"),
    NO_COLUMN_KEY_IN_UPSERT("\"column\" key is required in upsert option at index %s"),
    INVALID_TABLE_IN_UPSERT_OPTION("Invalid table in upsert object at index %s, table of type non empty string is required"),
    INVALID_COLUMN_IN_UPSERT_OPTION("Invalid column upsert object at index %s, column of type non empty string is required"),
    ALLOW_JSON_OBJECT_IN_UPSERT("upsert array should consists of json objects only"),
    EMPTY_UPSERT_OPTIONS_ARRAY("upsert option cannot be an empty array, atleast one object of table and column is required.")
}

fun Messages.getMessage(vararg values: String?): String{
    return Utils.constructMessage(this.message, *values)
}
