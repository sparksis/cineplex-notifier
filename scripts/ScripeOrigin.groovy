#!/usr/bin/groovy

@Grapes([
    @Grab(group='net.sourceforge.htmlunit', module='htmlunit', version='2.13'),
    @Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7.1')
])
import com.gargoylesoftware.htmlunit.WebClient;

import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*


def init(){
  WebClient webClient = new WebClient();
  webClient.getOptions().setJavaScriptEnabled(false);
  webClient.getOptions().setThrowExceptionOnScriptError(false);
  java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
  this.webClient = webClient;
}


def lookupMovies(pageUrl){
  println('TODO: lookup movies');
  def listingsPage = webClient.getPage(pageUrl);
  def listings = listingsPage.querySelectorAll('.showtime-card');
  def r = [];
  listings.each{
    if(it.getTextContent().contains("Buy Tickets")){
      return;
    }

    def listing = [:]

    def titleNode = it.querySelector('.showtime-card--title > a');
    def uri = titleNode.getAttributes().getNamedItem("href").getValue();
    listing.cineplexKey = uri.substring(uri.lastIndexOf("/")+1,uri.length());
    listing.name = titleNode.getTextContent();

    listing.thumbnailImageUrl = it.querySelector('img.dbsmallposter').getAttributes().getNamedItem("src").getValue();

    r.push(listing);
  }

  return r;
}

def loadListing(id){
  //TODO
  return null;
}

def findDescription(listing){
  //TODO
  return null;
}

def findThumbnail(listing){
  //TODO
  return null;
}

def findPosterImage(listing){
  //TODO
  return null;
}

def publish(movie){
  def http = new HTTPBuilder('http://localhost:8080')
  http.request( POST ) {
    uri.path = '/rest/movies/'
    requestContentType = JSON
    body =  movie

    response.success = { resp ->
      println "POST response status: ${resp.statusLine}"
      assert resp.statusLine.statusCode == 201
    }
  }
}

init();
def results = lookupMovies("http://www.cineplex.com/Movies/ComingSoon?cmpid=MainSubNavEN_coming-soon");

results.each{
  //TODO remove debugging code
  println "${it.name} (${it.cineplexKey})";
}

results.each{
    def listing = loadListing();
    it.descriptionHtml = findDescription(listing);
    it.thumbnailImage = findThumbnail(listing);
    it.posterImage = findPosterImage(listing);

    publish(it);
}
