package org.topnetwork.grpclib.xrpc;

import io.grpc.stub.ClientCalls;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.*;
import static io.grpc.stub.ServerCalls.*;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.11.0)",
    comments = "Source: xrpc.proto")
public final class xrpc_serviceGrpc {

  private xrpc_serviceGrpc() {}

  public static final String SERVICE_NAME = "top.xrpc_service";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @Deprecated // Use {@link #getCallMethod()} instead.
  public static final io.grpc.MethodDescriptor<xrpc_request,
          xrpc_reply> METHOD_CALL = getCallMethodHelper();

  private static volatile io.grpc.MethodDescriptor<xrpc_request,
          xrpc_reply> getCallMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<xrpc_request,
          xrpc_reply> getCallMethod() {
    return getCallMethodHelper();
  }

  private static io.grpc.MethodDescriptor<xrpc_request,
          xrpc_reply> getCallMethodHelper() {
    io.grpc.MethodDescriptor<xrpc_request, xrpc_reply> getCallMethod;
    if ((getCallMethod = xrpc_serviceGrpc.getCallMethod) == null) {
      synchronized (xrpc_serviceGrpc.class) {
        if ((getCallMethod = xrpc_serviceGrpc.getCallMethod) == null) {
          xrpc_serviceGrpc.getCallMethod = getCallMethod =
              io.grpc.MethodDescriptor.<xrpc_request, xrpc_reply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "top.xrpc_service", "call"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  xrpc_request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  xrpc_reply.getDefaultInstance()))
                  .setSchemaDescriptor(new xrpc_serviceMethodDescriptorSupplier("call"))
                  .build();
          }
        }
     }
     return getCallMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @Deprecated // Use {@link #getTableStreamMethod()} instead.
  public static final io.grpc.MethodDescriptor<xrpc_request,
          xrpc_reply> METHOD_TABLE_STREAM = getTableStreamMethodHelper();

  private static volatile io.grpc.MethodDescriptor<xrpc_request,
          xrpc_reply> getTableStreamMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<xrpc_request,
          xrpc_reply> getTableStreamMethod() {
    return getTableStreamMethodHelper();
  }

  private static io.grpc.MethodDescriptor<xrpc_request,
          xrpc_reply> getTableStreamMethodHelper() {
    io.grpc.MethodDescriptor<xrpc_request, xrpc_reply> getTableStreamMethod;
    if ((getTableStreamMethod = xrpc_serviceGrpc.getTableStreamMethod) == null) {
      synchronized (xrpc_serviceGrpc.class) {
        if ((getTableStreamMethod = xrpc_serviceGrpc.getTableStreamMethod) == null) {
          xrpc_serviceGrpc.getTableStreamMethod = getTableStreamMethod =
              io.grpc.MethodDescriptor.<xrpc_request, xrpc_reply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "top.xrpc_service", "table_stream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  xrpc_request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  xrpc_reply.getDefaultInstance()))
                  .setSchemaDescriptor(new xrpc_serviceMethodDescriptorSupplier("table_stream"))
                  .build();
          }
        }
     }
     return getTableStreamMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static xrpc_serviceStub newStub(io.grpc.Channel channel) {
    return new xrpc_serviceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static xrpc_serviceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new xrpc_serviceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static xrpc_serviceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new xrpc_serviceFutureStub(channel);
  }

  /**
   */
  public static abstract class xrpc_serviceImplBase implements io.grpc.BindableService {

    /**
     */
    public void call(xrpc_request request,
        io.grpc.stub.StreamObserver<xrpc_reply> responseObserver) {
      asyncUnimplementedUnaryCall(getCallMethodHelper(), responseObserver);
    }

    /**
     */
    public void tableStream(xrpc_request request,
        io.grpc.stub.StreamObserver<xrpc_reply> responseObserver) {
      asyncUnimplementedUnaryCall(getTableStreamMethodHelper(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCallMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                xrpc_request,
                      xrpc_reply>(
                  this, METHODID_CALL)))
          .addMethod(
            getTableStreamMethodHelper(),
            asyncServerStreamingCall(
              new MethodHandlers<
                xrpc_request,
                      xrpc_reply>(
                  this, METHODID_TABLE_STREAM)))
          .build();
    }
  }

  /**
   */
  public static final class xrpc_serviceStub extends io.grpc.stub.AbstractStub<xrpc_serviceStub> {
    private xrpc_serviceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private xrpc_serviceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected xrpc_serviceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new xrpc_serviceStub(channel, callOptions);
    }

    /**
     */
    public void call(xrpc_request request,
        io.grpc.stub.StreamObserver<xrpc_reply> responseObserver) {
      ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCallMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void tableStream(xrpc_request request,
        io.grpc.stub.StreamObserver<xrpc_reply> responseObserver) {
      ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getTableStreamMethodHelper(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class xrpc_serviceBlockingStub extends io.grpc.stub.AbstractStub<xrpc_serviceBlockingStub> {
    private xrpc_serviceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private xrpc_serviceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected xrpc_serviceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new xrpc_serviceBlockingStub(channel, callOptions);
    }

    /**
     */
    public xrpc_reply call(xrpc_request request) {
      return blockingUnaryCall(
          getChannel(), getCallMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<xrpc_reply> tableStream(
        xrpc_request request) {
      return blockingServerStreamingCall(
          getChannel(), getTableStreamMethodHelper(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class xrpc_serviceFutureStub extends io.grpc.stub.AbstractStub<xrpc_serviceFutureStub> {
    private xrpc_serviceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private xrpc_serviceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected xrpc_serviceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new xrpc_serviceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<xrpc_reply> call(
        xrpc_request request) {
      return futureUnaryCall(
          getChannel().newCall(getCallMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CALL = 0;
  private static final int METHODID_TABLE_STREAM = 1;

  private static final class MethodHandlers<Req, Resp> implements
      UnaryMethod<Req, Resp>,
      ServerStreamingMethod<Req, Resp>,
      ClientStreamingMethod<Req, Resp>,
      BidiStreamingMethod<Req, Resp> {
    private final xrpc_serviceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(xrpc_serviceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CALL:
          serviceImpl.call((xrpc_request) request,
              (io.grpc.stub.StreamObserver<xrpc_reply>) responseObserver);
          break;
        case METHODID_TABLE_STREAM:
          serviceImpl.tableStream((xrpc_request) request,
              (io.grpc.stub.StreamObserver<xrpc_reply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class xrpc_serviceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    xrpc_serviceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return HelloWorldProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("xrpc_service");
    }
  }

  private static final class xrpc_serviceFileDescriptorSupplier
      extends xrpc_serviceBaseDescriptorSupplier {
    xrpc_serviceFileDescriptorSupplier() {}
  }

  private static final class xrpc_serviceMethodDescriptorSupplier
      extends xrpc_serviceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    xrpc_serviceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (xrpc_serviceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new xrpc_serviceFileDescriptorSupplier())
              .addMethod(getCallMethodHelper())
              .addMethod(getTableStreamMethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
