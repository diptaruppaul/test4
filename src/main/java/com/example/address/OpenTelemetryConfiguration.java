package com.example.address;

import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.exporter.jaeger.JaegerGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;

public class OpenTelemetryConfiguration {

	static OpenTelemetry initOpenTelemetry(String jaegerHost, int jaegerPort) {

		Resource serviceNameResource =
				Resource.create(Attributes.of(ResourceAttributes.SERVICE_NAME, "otel-jaeger-example"));

		// Create a channel towards Jaeger end point    
		ManagedChannel jaegerChannel = ManagedChannelBuilder.forAddress(jaegerHost, jaegerPort).usePlaintext().build();

		// Export traces to Jaeger
		JaegerGrpcSpanExporter jaegerExporter =
				JaegerGrpcSpanExporter.builder()
				.setEndpoint("http://localhost:14250")
				.setChannel(jaegerChannel)
				.setTimeout(30, TimeUnit.SECONDS)
				.build();

		SdkTracerProvider tracerProvider =
				SdkTracerProvider.builder()
				.addSpanProcessor(SimpleSpanProcessor.create(jaegerExporter))
				.setResource(Resource.getDefault().merge(serviceNameResource))
				.build();

		OpenTelemetrySdk openTelemetry =
				OpenTelemetrySdk.builder().setTracerProvider(tracerProvider).build();

		// it's always a good idea to shut down the SDK cleanly at JVM exit.
		Runtime.getRuntime().addShutdownHook(new Thread(tracerProvider::close));

		return openTelemetry;
	}

}
