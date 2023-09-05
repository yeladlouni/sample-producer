import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerExample {
    public ProducerExample() {
        Properties settings = new Properties();

        // Using literals
        /*settings.put("client.id", "sensor-1");
        settings.put("bootstrap.servers", "localhost:9092");
        settings.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        settings.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");*/

        // Using classes
        settings.put(ProducerConfig.CLIENT_ID_CONFIG, "sensor-1");
        settings.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        settings.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        settings.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(settings);
        String topic = "transactions";

        for (int i=0; i < 1_000_000; i++) {
            String key = "key-" + i;
            String value = "value-" + i;
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
            producer.send(record);
        }
    }

    public static void main(String[] args) {
        new ProducerExample();
    }
}
