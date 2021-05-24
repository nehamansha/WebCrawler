
## Build

1. Maven
2. Java 8

## Build instructions

1. Run "mvn clean package"
2. Run "java -jar target/WebCrawler.jar"
3. At the command prompt provide a url, E.g https://wiprodigital.com/

## Omissions due to time constraints


1. Validations for duplicate urls
2. Parallel processing of links using futures(LinkExtractor)
3. Output could be written to a file
4. Exception handling
5. More test and logs


## Sample output
<pre>
External link: http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fcases%2Freimagining-supply-chain-apps-as-microservices-for-a-technology-leader%2F&hashtags=wiprodigital
Internal link: https://wiprodigital.com/privacy-policy/
--> Static Resource link: https://s17776.pcdn.co/wp-content/themes/wiprodigital/images/logo.png
--> Static Resource link: https://s17776.pcdn.co/wp-content/themes/wiprodigital/images/logo-dk-2X.png
</pre>
 



