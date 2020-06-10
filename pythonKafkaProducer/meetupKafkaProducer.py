from pykafka import KafkaClient
import requests

if __name__ == "__main__":
    client = KafkaClient(hosts="localhost:9092")
    topic = client.topics[bytes('pykafka',encoding='utf-8')]
    
    
while True:
    api_response = requests.get("http://stream.meetup.com/2/rsvps", stream=True)
    if api_response.status_code == 200:
        for message in api_response.iter_lines():
            with topic.get_sync_producer() as producer:
                producer.produce(message, )
                break




