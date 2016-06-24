# Netbank

![alt text](https://github.com/jesperlunpet/Netbank/blob/master/WebContent/Images/put-in.png "Put-in")

#### Mainframe

[http://192.86.32.54:9080/Put-in/](http://192.86.32.54:9080/Put-in/)

#### Localhost

Show the app on your browser: [http://localhost:9080/Put-in/](http://localhost:9080/Put-in/)

#### Connection to DB2

Add the following snippet to the server.xml file
After having copied the two .jar files into the correct folder
 /shared/resources/db2/

```xml
<dataSource id="db2" jndiName="jdbc/db2">
   	<jdbcDriver libraryRef="DB2Lib"/>
    	
   	<properties.db2.jcc databaseName="DALLASB" password="---" 
   		portNumber="5040" serverName="192.86.32.54" user="---"/>
</dataSource>
    
<library id="DB2Lib">
   	<fileset dir="${shared.resource.dir}/db2" includes="*.jar"/>
</library>
```

#### Currency data

[https://openexchangerates.org/api/latest.json?app_id=9d966ccd4fef4ff3ba3b48613802985a](https://openexchangerates.org/api/latest.json?app_id=9d966ccd4fef4ff3ba3b48613802985a)


