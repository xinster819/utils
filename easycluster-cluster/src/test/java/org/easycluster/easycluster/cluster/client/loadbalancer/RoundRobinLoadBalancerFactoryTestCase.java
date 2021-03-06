package org.easycluster.easycluster.cluster.client.loadbalancer;

import java.util.HashSet;
import java.util.Set;

import org.easycluster.easycluster.cluster.Node;
import org.easycluster.easycluster.cluster.client.loadbalancer.LoadBalancer;
import org.easycluster.easycluster.cluster.client.loadbalancer.LoadBalancerFactory;
import org.easycluster.easycluster.cluster.client.loadbalancer.RoundRobinLoadBalancerFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoundRobinLoadBalancerFactoryTestCase {

	private LoadBalancerFactory loadBalancerFactory = new RoundRobinLoadBalancerFactory();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNextNode() {
		Set<Node> nodes = new HashSet<Node>();
		nodes.add(new Node("localhost", 1111));
		nodes.add(new Node("localhost", 1112));
		nodes.add(new Node("localhost", 1113));
		nodes.add(new Node("localhost", 1114));
		nodes.add(new Node("localhost", 1115));
		nodes.add(new Node("localhost", 1116));
		nodes.add(new Node("localhost", 1117));
		nodes.add(new Node("localhost", 1118));
		nodes.add(new Node("localhost", 1119));
		nodes.add(new Node("localhost", 1120));
		nodes.add(new Node("localhost", 1120));
		nodes.add(new Node("localhost", 1120));

		for (Node node : nodes) {
			System.out.println("node: " + node);
			System.out.println("hashCode: " + node.hashCode());
		}

		LoadBalancer lb = loadBalancerFactory.newLoadBalancer(nodes);

		for (int i = 0; i < 100; i++) {
			Node node = lb.nextNode();
			System.out.println("nextNode: " + node);
			Assert.assertTrue(nodes.contains(node));
		}
	}

	@Test
	public void testEmptyNodes() {
		LoadBalancer lb = loadBalancerFactory
				.newLoadBalancer(new HashSet<Node>());
		Assert.assertNull(lb.nextNode());
	}

	@Test(expected = NullPointerException.class)
	public void testNull() {
		loadBalancerFactory.newLoadBalancer(null);
	}

}
