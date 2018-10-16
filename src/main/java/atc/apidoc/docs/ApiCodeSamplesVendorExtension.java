package atc.apidoc.docs;

import springfox.documentation.service.ListVendorExtension;

import java.util.List;


class ApiCodeSamplesVendorExtension
      extends ListVendorExtension<ApiCodeSamplesVendorExtension.Sample>
{
    ApiCodeSamplesVendorExtension( final String name,
                                   final List<ApiCodeSamplesVendorExtension.Sample> values )
    {
        super( name, values );
    }


    public static class Sample
    {
        private final String lang;
        private final String source;

        public Sample( final String lang,
                       final String source )
        {
            this.lang = lang;
            this.source = source;
        }

        public String getLang()
        {
            return lang;
        }

        public String getSource()
        {
            return source;
        }
    }
}
