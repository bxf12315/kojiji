/**
 * Copyright (C) 2015 Red Hat, Inc. (jcasey@redhat.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.red.build.koji.model.xmlrpc;

import org.commonjava.maven.atlas.ident.ref.ArtifactRef;
import org.commonjava.maven.atlas.ident.ref.ProjectRef;
import org.commonjava.maven.atlas.ident.ref.ProjectVersionRef;
import org.commonjava.rwx.anno.DataKey;
import org.commonjava.rwx.anno.StructPart;

import static com.redhat.red.build.koji.model.xmlrpc.KojiQuery.__STARSTAR;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

/**
 * Created by jdcasey on 5/6/16.
 */
@StructPart
public class KojiArchiveQuery extends KojiQuery
{
    @DataKey( value = "type" )
    private String type;

    @DataKey( value = "typeInfo" )
    private KojiMavenRef mavenRef;

    @DataKey( value = "filename" )
    private String filename;

    @DataKey( value = "buildID" )
    private Integer buildId;

    @DataKey( value = "buildrootID" )
    private Integer buildrootId;

    @DataKey( value = "componentBuildrootID" )
    private Integer componentBuildrootId;

    @DataKey( value = "hostID" )
    private Integer hostId;

    @DataKey( value = "size" )
    private Long size;

    @DataKey( value = "checksum" )
    private String checksum;

    public KojiArchiveQuery()
    {
    }

    public KojiArchiveQuery( ProjectRef ref )
    {
        this.mavenRef = new KojiMavenRef( ref );
        this.type = "maven";
        ArtifactRef ar = null;
        if ( ref instanceof ArtifactRef )
        {
            ar = (ArtifactRef) ref;
        }
        else if ( ref instanceof ProjectVersionRef )
        {
            ar = ( (ProjectVersionRef) ref ).asJarArtifact();
        }

        if ( ar != null )
        {
            StringBuilder sb = new StringBuilder();
            sb.append( ar.getArtifactId() ).append( '-' ).append( ar.getVersionString() );
            String classifier = ( ar ).getClassifier();
            if ( isNotEmpty( classifier ) )
            {
                sb.append( '-' ).append( classifier );
            }
            sb.append( '.' ).append( ( ar ).getType() );

            filename = sb.toString();
        }
    }

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }

    public KojiMavenRef getMavenRef()
    {
        return mavenRef;
    }

    public void setMavenRef( KojiMavenRef mavenRef )
    {
        this.mavenRef = mavenRef;
        this.type = "maven";
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename( String filename )
    {
        this.filename = filename;
    }

    public Integer getBuildId()
    {
        return buildId;
    }

    public void setBuildId( Integer buildId )
    {
        this.buildId = buildId;
    }

    public Integer getBuildrootId()
    {
        return buildrootId;
    }

    public void setBuildrootId( Integer buildrootId )
    {
        this.buildrootId = buildrootId;
    }

    public Integer getComponentBuildrootId()
    {
        return componentBuildrootId;
    }

    public void setComponentBuildrootId( Integer componentBuildrootId )
    {
        this.componentBuildrootId = componentBuildrootId;
    }

    public Integer getHostId()
    {
        return hostId;
    }

    public void setHostId( Integer hostId )
    {
        this.hostId = hostId;
    }

    public Long getSize()
    {
        return size;
    }

    public void setSize( Long size )
    {
        this.size = size;
    }

    public String getChecksum()
    {
        return checksum;
    }

    public void setChecksum( String checksum )
    {
        this.checksum = checksum;
    }

    public KojiArchiveQuery withFilename( String filename )
    {
        this.filename = filename;
        return this;
    }

    public KojiArchiveQuery withType( String type )
    {
        this.type = type;
        return this;
    }

    public KojiArchiveQuery withMavenRef( ProjectRef ref )
    {
        this.mavenRef = new KojiMavenRef( ref );
        this.type = "maven";
        return this;
    }

    public KojiArchiveQuery withMavenRef( KojiMavenRef ref )
    {
        this.mavenRef = ref;
        this.type = "maven";
        return this;
    }

    public KojiArchiveQuery withBuildId( Integer buildId )
    {
        this.buildId = buildId;
        return this;
    }

    public KojiArchiveQuery withBuildrootId( Integer buildrootId )
    {
        this.buildrootId = buildrootId;
        return this;
    }

    public KojiArchiveQuery withComponentBuildrootId( Integer componentBuildrootId )
    {
        this.componentBuildrootId = componentBuildrootId;
        return this;
    }

    public KojiArchiveQuery withSize( Long size )
    {
        this.size = size;
        return this;
    }

    public KojiArchiveQuery withChecksum( String checksum )
    {
        this.checksum = checksum;
        return this;
    }

    public KojiArchiveQuery withHostId( Integer hostId )
    {
        this.hostId = hostId;
        return this;
    }

    public KojiArchiveQuery withMavenGroupId( String groupId )
    {

        return this;
    }

    @Override
    public String toString()
    {
        return "KojiArchiveQuery{" +
                "type=" + type +
                ", mavenRef='" + mavenRef + '\'' +
                ", filename='" + filename + '\'' +
                ", buildId=" + buildId +
                ", buildrootId=" + buildrootId +
                ", componentBuildrootId=" + componentBuildrootId +
                ", hostId=" + hostId +
                ", size='" + size + '\'' +
                ", checksum=" + checksum +
                '}';
    }
}
