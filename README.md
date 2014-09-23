university test-job
==========

This is the source of the application.
I have also added this application to my server so it can be accessed at www.kaurkase.eu/university . User testUser/testPassword. May need to accept my self-signed certificate to access this.

The Idea was to create a university student information system.
As the task didn't define anything else it could have theoretically also been a command line application.
Task also didn't define the format of xml inputs so I based it on my Java classes.
I'm weak at unit testing, but did make a sample unit test that tests if java objects saved to xml files are converted back to the same objects from previously generated xml files.
When uploading xml files it is also necessary to choose which type of object(s) this xml file contains so that the application would be able to successfully parse it into objects.
There are 3 possible different xml files for 3 dataTypes: Course, Student and StudentCourse. All of this are represented in xml as lists and format can be seen from sample xml files provided in source root:
students.xml, courses.xml studentCourses.xml. Uploading those through the application will populate it with initial data.

I also added the possibility to edit data through the 'normal' means(html forms)
There is a lot of simple functions that could be added quite easily now, but I guess the idea is not to generate a fully working usable system, but to show off my skills so this should do I hope :)

Overall it took me a bit over 12 hours. Didn't count very accurately in the end as I was told it would take a good programmer 2-3 hours so I was disappointed in myself that it took me such a long time already.
I guess it could be done much much faster. A lot of time was spent on studying JAXB which I hadn't used before and thinking out the user interface and relevant url patterns.
Now I could use this project as a base for next similar things and have recalled some forgotten issues about hibernate so a next similar project would probably take a lot less time.