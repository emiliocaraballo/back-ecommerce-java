package co.com.ecommerce.domain.shared;

import java.util.function.Function;
import java.util.function.Supplier;

public class Either<L, R> {
    private final L left;
    private final R right;

    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Either<L, R> left(L value) {
        return new Either<>(value, null);
    }

    public static <L, R> Either<L, R> right(R value) {
        return new Either<>(null, value);
    }

    public boolean isLeft() {
        return left != null;
    }

    public boolean isRight() {
        return right != null;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    public <T> T fold(Function<? super L, ? extends T> leftMapper, Function<? super R, ? extends T> rightMapper) {
        return isLeft() ? leftMapper.apply(left) : rightMapper.apply(right);
    }

    public static <L, R> Either<L, R> run(Supplier<L> supplier, Function<Exception, R> errorMapper) {
        try {
            return left(supplier.get());
        } catch (Exception e) {
            return right(errorMapper.apply(e));
        }
    }
}