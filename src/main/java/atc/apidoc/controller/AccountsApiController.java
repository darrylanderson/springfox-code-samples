package atc.apidoc.controller;

import atc.apidoc.docs.ApiCodeSample;
import atc.apidoc.docs.ApiCodeSamples;
import atc.apidoc.model.Account;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Api( tags = { "Accounts" } )
@RestController
public class AccountsApiController
{
    private static final Logger LOGGER = LoggerFactory.getLogger( AccountsApiController.class );



    @ApiOperation( value = "Create account",
                   nickname = "createAccount",
                   notes = "Create a new account",
                   response = Account.class,
                   authorizations = {
                         @Authorization( value = "ApiKey" )
                   },
                   tags = { "Accounts" }
    )
    @ApiResponses( value = {
          @ApiResponse( code = 201,
                        message = "Account created successfully",
                        response = Account.class ) } )
    @ApiCodeSamples( samples = {
          @ApiCodeSample( lang = "Curl",
                          source = "curl --verbose -X POST \\\n" +
                                   "  https://myapiservice.mycompany.com/api/v1/accounts \\\n" +
                                   "  -H 'Accept: application/json' \\\n" +
                                   "  -H 'Authorization: Basic am9objpqb2hu...' \\\n" +
                                   "  -H 'Content-type: application/json' \\\n" +
                                   "  -d '{\n" +
                                   "\t\"userName\": \"myUser\",\n" +
                                   "\t\"email\": \"myuser@mycompany.com\",\n" +
                                   "\t\"phone\": \"555-555-5555\",\n" +
                                   "}'" ),
          @ApiCodeSample( lang = "Java",
                          source = "OkHttpClient client = new OkHttpClient();\n" +
                                   "\n" +
                                   "MediaType mediaType = MediaType.parse(\"application/json\");\n" +
                                   "RequestBody body = RequestBody.create(mediaType, \"{\\\"userName\\\": \\\"myUser\\\",\\\"email\\\": \\\"myuser@mycompany.com\\\",\\\"phone\\\": \\\"555-555-5555\\\"}\");\n" +
                                   "Request request = new Request.Builder()\n" +
                                   "  .url(\"https://myapiservice.mycompany.com/api/v1/accounts\")\n" +
                                   "  .post(body)\n" +
                                   "  .addHeader(\"content-type\", \"application/json\")\n" +
                                   "  .addHeader(\"accept\", \"application/json\")\n" +
                                   "  .addHeader(\"authorization\", \"Basic am9objpqb2hu...\")\n" +
                                   "  .build();\n" +
                                   "\n" +
                                   "Response response = client.newCall(request).execute();" )
    } )
    @ResponseStatus( HttpStatus.CREATED )
    @RequestMapping( value = "/accounts",
                     produces = { "application/json" },
                     consumes = { "application/json" },
                     method = RequestMethod.POST )
    public ResponseEntity<Account> createAccount( final Authentication authentication,
                                                  @ApiParam( value = "Account to create." ) @Valid @RequestBody final Account account )
    {
        // Do stuff....


        return new ResponseEntity<>( account, HttpStatus.CREATED );
    }
}
