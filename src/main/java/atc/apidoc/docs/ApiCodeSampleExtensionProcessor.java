package atc.apidoc.docs;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Processor to handle @ApiCodeSamples and @ApiCodeSample annotations, and add the resulting lang+code to an 'x-code-samples'
 * object which is rendered by ReDoc.
 */
@Component
@Order( SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 100 )
public class ApiCodeSampleExtensionProcessor
      implements OperationBuilderPlugin
{
    @Override
    public boolean supports( final DocumentationType documentationType )
    {
        // Only supports SWAGGER version 2.0
        return DocumentationType.SWAGGER_2.equals( documentationType );
    }

    @Override
    public void apply( final OperationContext operationContext )
    {
        final List<ApiCodeSamples> annotations = operationContext.findAllAnnotations( ApiCodeSamples.class );
        if ( !annotations.isEmpty() )
        {
            final ApiCodeSamples apiCodeSamples = annotations.get( 0 );
            final ApiCodeSample[] samples = apiCodeSamples.samples();

            final List<ApiCodeSamplesVendorExtension.Sample> list = new ArrayList<>();
            for ( final ApiCodeSample sample : samples )
            {
                final String lang = sample.lang();
                final String source = sample.source();
                list.add( new ApiCodeSamplesVendorExtension.Sample( lang, source ) );
            }

            final ApiCodeSamplesVendorExtension vendorExtension = new ApiCodeSamplesVendorExtension( "x-code-samples",
                                                                                                     list );

            operationContext.operationBuilder().extensions( Collections.singletonList( vendorExtension ) );
        }
    }
}
