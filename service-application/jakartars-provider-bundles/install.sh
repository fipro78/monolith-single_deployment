mvn install --file parent-pom.xml

mvn install:install-file \
  -Dfile=org.eclipse.ecf.provider.jakartars-1.0.0-SNAPSHOT.jar \
  -DgroupId=org.eclipse.ecf.jakartars \
  -DartifactId=org.eclipse.ecf.provider.jakartars \
  -Dversion=1.0.0-SNAPSHOT \
  -Dpackaging=jar
  

mvn install:install-file \
  -Dfile=org.eclipse.ecf.provider.jakartars.server-1.0.0-SNAPSHOT.jar \
  -DgroupId=org.eclipse.ecf.jakartars \
  -DartifactId=org.eclipse.ecf.provider.jakartars.server \
  -Dversion=1.0.0-SNAPSHOT \
  -Dpackaging=jar
  
 
mvn install:install-file \
  -Dfile=org.eclipse.ecf.provider.jersey.server-1.0.0-SNAPSHOT.jar \
  -DgroupId=org.eclipse.ecf.jakartars \
  -DartifactId=org.eclipse.ecf.provider.jersey.server \
  -Dversion=1.0.0-SNAPSHOT \
  -Dpackaging=jar
  
  
mvn install:install-file \
  -Dfile=org.eclipse.ecf.provider.jakartars.client-1.0.0-SNAPSHOT.jar \
  -DgroupId=org.eclipse.ecf.jakartars \
  -DartifactId=org.eclipse.ecf.provider.jakartars.client \
  -Dversion=1.0.0-SNAPSHOT \
  -Dpackaging=jar
  
  
mvn install:install-file \
  -Dfile=org.eclipse.ecf.provider.jersey.client-1.0.0-SNAPSHOT.jar \
  -DgroupId=org.eclipse.ecf.jakartars \
  -DartifactId=org.eclipse.ecf.provider.jersey.client \
  -Dversion=1.0.0-SNAPSHOT \
  -Dpackaging=jar