package idea;

public class BinaryTree2 {

    // 시간 복잡도 O(h), h는 트리의 높이 => 평균 : O(logN), 최악 : O(N)
    // search(x, k) 재귀 함수 이용
    // if x == NIL || k key[x]
    //     then return x;
    // if k < key[x]
    //     then return search(left[x], k)
    //     else return search(right[x], k)


    // searchAsIteration(x, k) 반복문 이용 => 평균 : O(logN), 최악 : O(N)
    // while x != null && k != key[x]
    //     do if k < key[x]
    //            then x <- left[x]
    // return x;


    // 시간 복잡도 : O(h) => 평균 : O(logN), 최악 : O(N)
    // 최소값은 항상 가장 왼쪽 노드에 존재
    // getMinimum(x)
    // while left[x] != null
    //     do x <- left[x]
    // return x;


    // 시간 복잡도 : O(h) => 평균 : O(logN), 최악 : O(N)
    // 최대값은 항상 가장 오른쪽 노드에 존재
    // getMaximum(x)
    // while right[x] != null
    //     do x <- right[x]
    // return x;


    // Successor 는 해당 노드의 값보다 큰 값들중 가장 작은 값
    // 시간 복잡도 : O(h) => 평균 : O(logN), 최악 : O(N)
    // getSuccessor(x)
    //     if right[x] != null
    //         then return getMinimum(right[x])
    // y <- p[x]
    // while y != null and x == right[y]
    //     do x <- y
    //        y <- p[y]
    // return y;


    // 시간 복잡도 : O(h) => 평균 : O(logN), 최악 : O(N)
    // insert(T, z)
    //     y <- null
    //     x <- root[T]
    //     while x != null
    //         do y <- x
    //             if key[z] < key[x]
    //                 then x <- left[x]
    //                 else x <- right[x]
    // p[z] <- y
    // if y = null
    //     then root[T] <- z
    //     else if key[z] < key[y] && Tree T was empty
    //         then left left[y] <- z
    //         else right[y] <- z


    // 시간 복잡도 : O(h) => 평균 : O(logN), 최악 : O(N)
    // delete(T, z)
    //     if left[z] == null or right[z] == null
    //         then y <- z
    //         else y <- getSuccessor(z)
    //     if left[y] != null
    //         then x <- left[y]
    //         else x <- right[y]
    //     if x != null
    //         then p[x] <- p[y]
    //     if p[y] == null
    //         then root[T] <- x
    //         else if y == left[p[y]]
    //                 then left[p[y]] <- x
    //                 else right[p[y]] <- x
    //     if y != z
    //         then key[z] <- key[y]
    //             copy y's statellite data into z
    //     return y;
}
