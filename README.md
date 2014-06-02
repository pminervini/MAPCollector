TODO:
	Make a Spring-based project.

Check:	http://spring.io/guides/gs/messaging-jms/

Note:
	Some of the PugliaEvents entries have the following description:
		"24 agosto 2014 - Salento Enogastronomico - (LE)."
	However, there is no such place; it should be possible to handle such cases,
	e.g. by using custom geolocation functions, wrappers, macros and such.

	Q: Is there any hint on the place within the article?
	A: For "24 agosto 2014 - Salento Enogastronomico - (LE)" not really .. I'm also looking up the address replacing "(LE)" with "Lecce" etc.

	Q: How to handle the cases in which the location is unknown?
	A: By now let's just avoid adding the news entries (is this the right solution? maybe it's too drastic ..)
		Yes it is; now the "location" entry can be null.
