This is a small proof of how a PropertyPlaceholderConfigurer can be used to simplify reading composite environment variables such as a database URL that encodes username, password and driver in the URL string and replacing the appropriate propery placeholders in a spring config with those values.

The code her works with both Spring 2.5.6, Spring 3.0 and Spring 3.1.

With this approach, all you have to do to configure the database in *most* spring configs is to add this bean config at the top of your context config file (before the normal property placeholder). If DATABASE_URL is available in the environment it will override normal placeholders.

Of course you also need to pull in this code, so you'll need to add one more dependency to your project.

