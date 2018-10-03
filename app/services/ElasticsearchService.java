package services;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import play.Logger;
import play.inject.ApplicationLifecycle;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Singleton
public class ElasticsearchService {

	private static final String CLUSTER_NAME = "es_cluster";
	private static final String NODE_IP = "127.0.0.1";
	private static final int PORT = 9300;

	private Client client;

	@Inject
	public ElasticsearchService(ApplicationLifecycle lifecycle) {

		client = createEsClient();

		lifecycle.addStopHook(() -> {
			if (client != null) {
				client.close();
				client = null;
			}

			return null;
		});
	}

	private Client createEsClient() {
		Logger.info("Creating ES Client");

		Settings settings = Settings.builder().put("cluster.name", CLUSTER_NAME).build();

		TransportClient transportClient = new PreBuiltTransportClient(settings);


		try {

			TransportAddress transportAddress = new InetSocketTransportAddress(InetAddress.getByName(NODE_IP), PORT);

			transportClient.addTransportAddress(transportAddress);

		} catch (UnknownHostException e) {
			Logger.error(e.getMessage(), e);
		}


		for (DiscoveryNode node : transportClient.connectedNodes()) {
			Logger.info("Connected to node " + node);
		}

		return transportClient;
	}

	public Client getClient() {
		return client;
	}
}
