package grpccalculator.server;

import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.Calculator.OperationParam;
import com.proto.calculator.Calculator.OperationResult;

import io.grpc.stub.StreamObserver;

public class CalculatorServiceImplementation extends CalculatorServiceGrpc.CalculatorServiceImplBase {
    @Override
    public void add(OperationParam params, StreamObserver<OperationResult> resultObserver) {
        OperationResult result = OperationResult.newBuilder().setResult(params.getA() + params.getB()).build();

        resultObserver.onNext(result);

        resultObserver.onCompleted();
    }

    @Override
    public void substract(OperationParam params, StreamObserver<OperationResult> resultObserver) {
        OperationResult result = OperationResult.newBuilder().setResult(params.getA() - params.getB()).build();

        resultObserver.onNext(result);

        resultObserver.onCompleted();
    }

    @Override
    public void multiply(OperationParam params, StreamObserver<OperationResult> resultObserver) {
        OperationResult result = OperationResult.newBuilder().setResult(params.getA() * params.getB()).build();

        resultObserver.onNext(result);

        resultObserver.onCompleted();
    }

    @Override
    public void divide(OperationParam params, StreamObserver<OperationResult> resultObserver) {
        OperationResult result = OperationResult.newBuilder().setResult(params.getA() / params.getB()).build();

        resultObserver.onNext(result);

        resultObserver.onCompleted();
    }
}
