package com.demo.schema

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

