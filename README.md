# DSProject
By Alina Danci 

##A Vigenère Cypher Breaker Functionality

KeyEnumerator.java: A class capable of generating the complete enumeration of keys with a key length of 3 – maxKeyLength.
Vigenere.java: An implementation of the Vigenere cypher, capable of encrypting and decrypting a message. You will need this class to decrypt cypher text with a generated key.
cracker.war and cracker.zip: A web application archive containing the form (JSP) and handler servlet (commented) required for the project. The archive also contains a fully commented deployment descriptor in WEB-INF/web.xml and shows how application contexts and form information can be accessed.
quadgrams.zip: A Zip archive containing a set of 389,373 English quadgrams and their frequencies.
WarAndPeace.zip: A Zip archive containing the text of Leo Tolstoy’s opus ‘War and Peace’. With a file size of 3.2Mb, there are circa 800,000 quadgrams available in the text.

##Method behind coding 
I first created the the RMIEncryption which includes KeyEnumerator, QuadgramMap, Vigenere, VigenereBreaker, VigenereBreakerImpl.
Then i focused my attention on creating a blocked queue and map.Which will allow messages go through the Blocked queue and then put out on a map.
I then created a vigenere.jar.
