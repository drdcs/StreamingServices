# Spark Streaming example
## Use Case:
```

# Kafka producer -> reads the information from meetup group meetup.com
# Spark Structured Streaming -> use spark to read the format as kafka.
# Jdbc Load of events and Venue details -> Load a SQL server Table for specific information.

Markup :  ## What you will learn ?##

* Design a kafka producer via pykafka.
* How to design a case class for complex json.
* A spark structure streaming read from kafka as a source.
* How to do jdbc Load for writestream  through foreach batch.

```
Tech Used: Kafka, Spark
Programming Language: Scala and python.


The producer via pykafka (python) listens realtime data from a meetup group.
The data in return is complex Json.

```Snippet
{
  "venue": {
    "venue_name": "16109 Nacogdoches Rd",
    "lon": -98.36081,
    "lat": 29.587322,
    "venue_id": 26466187
  },
  "visibility": "public",
  "response": "yes",
  "guests": 0,
  "member": {
    "member_id": 200069824,
    "photo": "https://secure.meetupstatic.com/photos/member/e/9/e/c/thumb_254039884.jpeg",
    "member_name": "Debbie LaBouff Burnam"
  },
  "rsvp_id": 1840421555,
  "mtime": 1591671730500,
  "event": {
    "event_name": "SISTA's @ Pompeii ",
    "event_id": "tbhmgrybcjbnb",
    "time": 1591806600000,
    "event_url": "https://www.meetup.com/meetup-group-VVwOORPh/events/271065743/"
  },
  "group": {
    "group_topics": [
      {
        "urlkey": "smallbiz",
        "topic_name": "Small Business"
      },
      {
        "urlkey": "business-referral-networking",
        "topic_name": "Business Referral Networking"
      },
      {
        "urlkey": "women-entrepreneurs",
        "topic_name": "Women Entrepreneurs"
      },
      {
        "urlkey": "business-strategy",
        "topic_name": "Business Strategy"
      },
      {
        "urlkey": "professional-women",
        "topic_name": "Professional Women"
      },
      {
        "urlkey": "professional-networking",
        "topic_name": "Professional Networking"
      },
      {
        "urlkey": "womens-business-networking",
        "topic_name": "Women's Business Networking"
      },
      {
        "urlkey": "womens-networking",
        "topic_name": "Women's Networking"
      },
      {
        "urlkey": "networking-your-network-marketing-business",
        "topic_name": "Networking Your Network Marketing Business"
      }
    ],
    "group_city": "San Antonio",
    "group_country": "us",
    "group_id": 32201506,
    "group_name": "Sista's",
    "group_lon": -98.47,
    "group_urlname": "meetup-group-VVwOORPh",
    "group_state": "TX",
    "group_lat": 29.48
  }
}
```
As you can see it's a nested json and we can use a Case class to read  with format tie to the json.

schema below:

```

case class MeetUpCaseClass(
                   venue: VenueDetails,
                   visibility: Option[String],
                   response: Option[String],
                   guests: Option[String],
                   member: MemberDetails,
                   rsvp_id: Option[Long],
                   mtime: Option[Long],
                   event: EventDetails,
                   group: GroupDetails
                 )

case class GroupDetails (group_topics: Array[GroupTopics],
                         group_city: Option[String],
                         group_country: Option[String],
                         group_id: Long,
                         group_name: Option[String],
                         group_lon: Option[Float],
                         group_urlname: Option[String],
                         group_state: Option[String],
                         group_lat: Option[String])

case class VenueDetails (venue_name: Option[String], lon: Option[Float], lat: Option[Float], venue_id:Option[Long])
case class MemberDetails (member_id: Long, photo: Option[String], member_name: Option[String] )
case class  EventDetails (event_name: Option[String], event_id: Option[String], time: Long, event_url:String)
case class GroupTopics(urlkey: Option[String], topic_name: Option[String])

``` 

### refer the page: src/main/scala/com/demo/schema

The data then finally loaded into the SQL Server through structured Streaming for each batch.


