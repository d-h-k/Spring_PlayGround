package helloadvanced.advenced.trace.logtrace;

import helloadvanced.advenced.trace.TraceId;
import helloadvanced.advenced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

//@todo W3 버그가 있는거같은데 질문올림 : https://www.inflearn.com/questions/382399
@Slf4j
public class ThreadLocalLogTrace implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    //필드변수이지만 쓰레드마다 고유한 영역이 제공되므로 동시성 문제가 해결
    private final ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

    @Override
    public TraceStatus begin(String message) {

        syncTraceId();
        TraceId traceId = traceIdHolder.get();
        Long startTimeMs = System.currentTimeMillis();

        log.info("[{}] {},{}",
                 traceId.getId(),
                 addSpace(START_PREFIX, traceId.getLevel()),
                 message);

        return new TraceStatus(traceId, startTimeMs, message);
    }

    public void syncTraceId() {
        TraceId traceId = traceIdHolder.get();
        if (traceId == null) {
            //널, 없으면 새로운값 넣어줌
            traceIdHolder.set(new TraceId());
        } else {
            //널이 아니면 넥스트로
            traceIdHolder.set(traceId.createNextId());
        }
    }

    static String addSpace(String prefix, int level) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < level; i++) {
            builder.append((i == level - 1) ? "|" + prefix : "| ");
        }
        return builder.toString();
    }


    public void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTime = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();

        if (e == null) {
            log.info("OK:[{}] {},{} time={}ms OK!!",
                     traceId.getId(),
                     addSpace(COMPLETE_PREFIX, traceId.getLevel()),
                     status.getMessage(),
                     resultTime);
        } else {
            log.info("EX:[{}] {},{} time={}ms ex={}",
                     traceId.getId(),
                     addSpace(EX_PREFIX, traceId.getLevel()),
                     status.getMessage(),
                     resultTime,
                     e.toString());
        }
        releaseTraceId();
    }

    public void releaseTraceId() {
        TraceId traceId = traceIdHolder.get();
        if (traceId.isFirstLevel()) {
            // 첫번째 레벨이라면 - 제거, 해당 쓰레드로컬에 저장한 데이터가 제거됨
            traceIdHolder.remove();
            //todo W2 : 이거 중요한게 쓰레드로컬은 다 쓰면 항상 제거해줘야 한다
        } else {
            // 첫번째 레벨이 아니라면 - 이전Id SET
            traceIdHolder.set(traceId.createPreviousId());
        }
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }


    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }
}
