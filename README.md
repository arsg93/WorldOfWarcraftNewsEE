# WorldOfWarcraftNewsEE

Project base:https://github.com/arsg93/Noticias by [arsg93](https://github.com/arsg93)


TRY [HERE](http://news1.news80103.private.jelastic.cloud/WorldOfWarcraftNewsEE-war/) 


## Description

Java EE project on a news website, where you can add new and visualize them using jsp.

## Done

<ul>
<li>The page manage news using EJB i PostGreSQL</li>
<li>Two WARs have been made, one for admins another for users</li>
<li>Users registered can add news</li>
<li>All users can see the news</li>
<li>All users a news item in detail</li>
<li>The app should create the tables if doesn't exists the first time that the server run the page</li>
<li>The sistem use glassfish authentication to add news</li>
<li>The app should be exported to a PASS</li>
</ul>



## Changelog


v0 - The repository has been created forked from the project base 
     [Noticias](https://github.com/arsg93/Noticias)
     by [arsg93](https://github.com/arsg93)

v1 - Data base definition and JPA entities with controllers created

v2 - Has been created the page to add news and works correctly

v3 - Has been the jsp to see all the news and works correctly

v4 - Has been the see a news item in detail and works correctly

v5 - The links to share on social networks have been edited

v6 - A new war has been created which only contains the actions of the standard user

v7 - Access restrictions have been added to the entire Admins war with Glassfish

v8 - A shared resource has been added in Glassfish to allow viewing of the image folder between the WARs.
