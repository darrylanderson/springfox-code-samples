package atc.apidoc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;


@ApiModel
public class Account
{
    private static final Logger LOGGER = LoggerFactory.getLogger( Account.class );


    @ApiModelProperty( value = "The id of the account.",
                       position = 10,
                       accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                       readOnly = true )
    private Long id;

    @NotNull
    @ApiModelProperty( required = true,
                       value = "The username of the account.",
                       position = 20 )
    private String userName = null;

    @NotNull
    @ApiModelProperty( value = "The email address of the account.",
                       required = true,
                       position = 30 )
    private String email = null;

    @NotNull
    @ApiModelProperty( value = "The phone number of the account.",
                       required = true,
                       position = 40 )
    private String phone = null;

    @ApiModelProperty( value = "Date the account was created.",
                       position = 1000,
                       accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                       readOnly = true )
    private OffsetDateTime createdDate = null;

    @ApiModelProperty( value = "Date the account was last updated.",
                       position = 1010,
                       accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                       readOnly = true )
    private OffsetDateTime lastModifiedDate = null;



    public Long getId()
    {
        return id;
    }

    public void setId( final Long id )
    {
        this.id = id;
    }

    /**
     * Get name
     *
     * @return name
     **/
    @NotNull
    public String getUserName()
    {
        return userName;
    }

    public void setUserName( final String userName )
    {
        this.userName = userName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( final String email )
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone( final String phone )
    {
        this.phone = phone;
    }

    public Account createdDate( final OffsetDateTime createdDate )
    {
        this.createdDate = createdDate;
        return this;
    }

    /**
     * Get createdDate
     *
     * @return createdDate
     **/
    @ApiModelProperty( value = "The creation date of this account." )
    @Valid
    public OffsetDateTime getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate( final OffsetDateTime createdDate )
    {
        this.createdDate = createdDate;
    }

    public Account lastModifiedDate( final OffsetDateTime lastModifiedDate )
    {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    /**
     * Get lastModifiedDate
     *
     * @return lastModifiedDate
     **/
    @ApiModelProperty( value = "The last modification date of this account." )
    @Valid
    public OffsetDateTime getLastModifiedDate()
    {
        return lastModifiedDate;
    }

    public void setLastModifiedDate( final OffsetDateTime lastModifiedDate )
    {
        this.lastModifiedDate = lastModifiedDate;
    }
}

