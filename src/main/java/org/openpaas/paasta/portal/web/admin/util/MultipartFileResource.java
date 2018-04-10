package org.openpaas.paasta.portal.web.admin.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileResource extends AbstractResource {

    private final MultipartFile multipartFile;
    
    private final String description;

    private boolean read = false;
    
    public MultipartFileResource(final MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
        this.description = "MultipartFile@" + Integer.toHexString( this.multipartFile.hashCode() ) + "(" + multipartFile.getOriginalFilename() + ")";
    }
    
    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "MultipartFile resource [" + this.description + "]";
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if (this.read) {
            throw new IllegalStateException("InputStream has already been read - " +
                    "do not use InputStreamResource if a stream needs to be read multiple times");
        }
        this.read = true;
        return this.multipartFile.getInputStream();
    }
    
    
    @Override
    public boolean exists() {
        return (false == this.multipartFile.isEmpty());
    }
    
    @Override
    public boolean isReadable() {
        return true;
    }
    
    @Override
    public boolean isOpen() {
        // read is open.
        return this.read;
    }
    
    public String getFilename() {
        return this.multipartFile.getOriginalFilename();
    }
    
    public String toString() {
        return getDescription();
    }
    
    @Override
    public boolean equals( Object obj ) {
        return ( obj == this || 
            ( (obj instanceof MultipartFileResource) && ((MultipartFileResource) obj ).getDescription().equals( getDescription() )) );
    }
    
    @Override
    public int hashCode() {
        return getDescription().hashCode();
    }
    
    @Override
    public URL getURL() throws IOException {
        throw new UnsupportedOperationException( "MultipartFileResource cannot support this operation." );
    }
    
    @Override
    public URI getURI() throws IOException {
        throw new UnsupportedOperationException( "MultipartFileResource cannot support this operation." );
    }
    
    @Override
    public File getFile() throws IOException {
        throw new UnsupportedOperationException( "MultipartFileResource cannot support this operation." );
    }
    
    @Override
    public long contentLength() throws IOException {
        return this.multipartFile.getSize();
    }
    
    @Override
    public long lastModified() throws IOException {
        throw new UnsupportedOperationException( "MultipartFileResource cannot support this operation." );
    }
    
    public Resource createRelative(String relativePath) throws IOException {
        throw new UnsupportedOperationException( "MultipartFileResource cannot support this operation." );
    }
}
