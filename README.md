# Netbank

#### Localhost

Show the app on your browser: [http://localhost:9080/Netbank/](http://localhost:9080/Netbank/)

#### Connection to DB2

Add the following snippet to the server.xml file

```xml
<dataSource id="db2" jndiName="jdbc/db2">
   	<jdbcDriver libraryRef="DB2Lib"/>
    	
   	<properties.db2.jcc databaseName="DALLASB" password="FAGP2016" portNumber="5040" serverName="192.86.32.54" user="DTU09"/>
</dataSource>
    
<library id="DB2Lib">
   	<fileset dir="${shared.resource.dir}/db2" includes="*.jar"/>
</library>
```

#### Currency data

[https://openexchangerates.org/api/latest.json?app_id=9d966ccd4fef4ff3ba3b48613802985a](https://openexchangerates.org/api/latest.json?app_id=9d966ccd4fef4ff3ba3b48613802985a)

#### Mainframe

Login to change the files in the Mainframe:

Jesper: DTU08
Morten: DTU09

Password: FAGP2016
