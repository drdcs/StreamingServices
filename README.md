# Spark Streaming example
## Use Case:
```
This is a Realtime data Reader from meetup group.
The complex Json is being read by spark through a case class.
Then section of data to move into sql server table.

```
Tech Used: Kafka, Python, Spark, Scala

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
As you can see it's a nested json.

I want to read this via spark streaming as a Case class.

### refer the page: src/main/scala/com/demo/schema

The data then finally loaded into the SQL Server through structured Streaming for each batch.


