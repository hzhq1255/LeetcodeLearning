#### 方法一：回溯

数组 ![\textit{nums} ](./p__textit{nums}_.png)  的每个元素都可以添加符号 ![\texttt{+} ](./p__texttt{+}_.png)  或 ![\texttt{-} ](./p__texttt{-}_.png) ，因此每个元素有 *2* 种添加符号的方法，*n* 个数共有 *2^n* 种添加符号的方法，对应 *2^n* 种不同的表达式。当 *n* 个元素都添加符号之后，即得到一种表达式，如果表达式的结果等于目标数 ![\textit{target} ](./p__textit{target}_.png) ，则该表达式即为符合要求的表达式。

可以使用回溯的方法遍历所有的表达式，回溯过程中维护一个计数器 ![\textit{count} ](./p__textit{count}_.png) ，当遇到一种表达式的结果等于目标数 ![\textit{target} ](./p__textit{target}_.png)  时，将 ![\textit{count} ](./p__textit{count}_.png)  的值加 *1*。遍历完所有的表达式之后，即可得到结果等于目标数 ![\textit{target} ](./p__textit{target}_.png)  的表达式的数目。

```Java [sol1-Java]
class Solution {
    int count = 0;

    public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    public void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }
}
```

```C# [sol1-C#]
public class Solution {
    int count = 0;

    public int FindTargetSumWays(int[] nums, int target) {
        Backtrack(nums, target, 0, 0);
        return count;
    }

    public void Backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.Length) {
            if (sum == target) {
                count++;
            }
        } else {
            Backtrack(nums, target, index + 1, sum + nums[index]);
            Backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }
}
```

```JavaScript [sol1-JavaScript]
var findTargetSumWays = function(nums, target) {
    let count = 0;
    const backtrack = (nums, target, index, sum) => {
        if (index === nums.length) {
            if (sum === target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }
    
    backtrack(nums, target, 0, 0);
    return count;
};
```

```go [sol1-Golang]
func findTargetSumWays(nums []int, target int) (count int) {
    var backtrack func(int, int)
    backtrack = func(index, sum int) {
        if index == len(nums) {
            if sum == target {
                count++
            }
            return
        }
        backtrack(index+1, sum+nums[index])
        backtrack(index+1, sum-nums[index])
    }
    backtrack(0, 0)
    return
}
```

```C++ [sol1-C++]
class Solution {
public:
    int count = 0;

    int findTargetSumWays(vector<int>& nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    void backtrack(vector<int>& nums, int target, int index, int sum) {
        if (index == nums.size()) {
            if (sum == target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }
};
```

```C [sol1-C]
int count;

int findTargetSumWays(int* nums, int numsSize, int target) {
    count = 0;
    backtrack(nums, numsSize, target, 0, 0);
    return count;
}

void backtrack(int* nums, int numSize, int target, int index, int sum) {
    if (index == numSize) {
        if (sum == target) {
            count++;
        }
    } else {
        backtrack(nums, numSize, target, index + 1, sum + nums[index]);
        backtrack(nums, numSize, target, index + 1, sum - nums[index]);
    }
}
```

**复杂度分析**

- 时间复杂度：*O(2^n)*，其中 *n* 是数组 ![\textit{nums} ](./p__textit{nums}_.png)  的长度。回溯需要遍历所有不同的表达式，共有 *2^n* 种不同的表达式，每种表达式计算结果需要 *O(1)* 的时间，因此总时间复杂度是 *O(2^n)*。

- 空间复杂度：*O(n)*，其中 *n* 是数组 ![\textit{nums} ](./p__textit{nums}_.png)  的长度。空间复杂度主要取决于递归调用的栈空间，栈的深度不超过 *n*。

#### 方法二：动态规划

记数组的元素和为 ![\textit{sum} ](./p__textit{sum}_.png) ，添加 ![\texttt{-} ](./p__texttt{-}_.png)  号的元素之和为 ![\textit{neg} ](./p__textit{neg}_.png) ，则其余添加 ![\texttt{+} ](./p__texttt{+}_.png)  的元素之和为 ![\textit{sum}-\textit{neg} ](./p__textit{sum}-textit{neg}_.png) ，得到的表达式的结果为 

![(\textit{sum}-\textit{neg})-\textit{neg}=\textit{sum}-2\cdot\textit{neg}=\textit{target} ](./p____textit{sum}-textit{neg}_-textit{neg}=textit{sum}-2cdottextit{neg}=textit{target}__.png) 

即 

![\textit{neg}=\dfrac{\textit{sum}-\textit{target}}{2} ](./p___textit{neg}=dfrac{textit{sum}-textit{target}}{2}__.png) 

由于数组 ![\textit{nums} ](./p__textit{nums}_.png)  中的元素都是非负整数，![\textit{neg} ](./p__textit{neg}_.png)  也必须是非负整数，所以上式成立的前提是 ![\textit{sum}-\textit{target} ](./p__textit{sum}-textit{target}_.png)  是**非负偶数**。若不符合该条件可直接返回 *0*。

若上式成立，问题转化成在数组 ![\textit{nums} ](./p__textit{nums}_.png)  中选取若干元素，使得这些元素之和等于 ![\textit{neg} ](./p__textit{neg}_.png) ，计算选取元素的方案数。我们可以使用动态规划的方法求解。

定义二维数组 ![\textit{dp} ](./p__textit{dp}_.png) ，其中 ![\textit{dp}\[i\]\[j\] ](./p__textit{dp}_i__j__.png)  表示在数组 ![\textit{nums} ](./p__textit{nums}_.png)  的前 *i* 个数中选取元素，使得这些元素之和等于 *j* 的方案数。假设数组 ![\textit{nums} ](./p__textit{nums}_.png)  的长度为 *n*，则最终答案为 ![\textit{dp}\[n\]\[\textit{neg}\] ](./p__textit{dp}_n__textit{neg}__.png) 。

当没有任何元素可以选取时，元素和只能是 *0*，对应的方案数是 *1*，因此动态规划的边界条件是：

![\textit{dp}\[0\]\[j\]=\begin{cases}1,&j=0\\0,&j\ge1\end{cases} ](./p___textit{dp}_0__j_=begin{cases}_1,_&_j=0__0,_&_j_ge_1_end{cases}__.png) 

当 ![1\lei\len ](./p__1_le_i_le_n_.png)  时，对于数组 ![\textit{nums} ](./p__textit{nums}_.png)  中的第 *i* 个元素 ![\textit{num} ](./p__textit{num}_.png) （*i* 的计数从 *1* 开始），遍历 ![0\lej\le\textit{neg} ](./p__0_le_j_le_textit{neg}_.png) ，计算 ![\textit{dp}\[i\]\[j\] ](./p__textit{dp}_i__j__.png)  的值：

- 如果 ![j<\textit{num} ](./p__j___textit{num}_.png) ，则不能选 ![\textit{num} ](./p__textit{num}_.png) ，此时有 ![\textit{dp}\[i\]\[j\]=\textit{dp}\[i-1\]\[j\] ](./p__textit{dp}_i__j__=_textit{dp}_i_-_1__j__.png) ；

- 如果 ![j\ge\textit{num} ](./p__j_ge_textit{num}_.png) ，则如果不选 ![\textit{num} ](./p__textit{num}_.png) ，方案数是 ![\textit{dp}\[i-1\]\[j\] ](./p__textit{dp}_i_-_1__j__.png) ，如果选 ![\textit{num} ](./p__textit{num}_.png) ，方案数是 ![\textit{dp}\[i-1\]\[j-\textit{num}\] ](./p__textit{dp}_i_-_1__j_-_textit{num}__.png) ，此时有 ![\textit{dp}\[i\]\[j\]=\textit{dp}\[i-1\]\[j\]+\textit{dp}\[i-1\]\[j-\textit{num}\] ](./p__textit{dp}_i__j__=_textit{dp}_i_-_1__j__+_textit{dp}_i_-_1__j_-_textit{num}__.png) 。

因此状态转移方程如下：

![\textit{dp}\[i\]\[j\]=\begin{cases}\textit{dp}\[i-1\]\[j\],&j<\textit{nums}\[i\]\\\textit{dp}\[i-1\]\[j\]+\textit{dp}\[i-1\]\[j-\textit{nums}\[i\]\],&j\ge\textit{nums}\[i\]\end{cases} ](./p___textit{dp}_i__j_=begin{cases}_textit{dp}_i_-_1__j_,_&_j_textit{nums}_i___textit{dp}_i_-_1__j__+_textit{dp}_i_-_1__j_-_textit{nums}_i__,_&_j_ge_textit{nums}_i__end{cases}__.png) 

最终得到 ![\textit{dp}\[n\]\[\textit{neg}\] ](./p__textit{dp}_n__textit{neg}__.png)  的值即为答案。

由此可以得到空间复杂度为 ![O(n\times\textit{neg}) ](./p__O_n_times_textit{neg}__.png)  的实现。

```Java [sol2-Java]
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];
    }
}
```

```C# [sol2-C#]
public class Solution {
    public int FindTargetSumWays(int[] nums, int target) {
        int sum = 0;
        foreach (int num in nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.Length, neg = diff / 2;
        int[,] dp = new int[n + 1, neg + 1];
        dp[0, 0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i, j] = dp[i - 1, j];
                if (j >= num) {
                    dp[i, j] += dp[i - 1, j - num];
                }
            }
        }
        return dp[n, neg];
    }
}
```

```JavaScript [sol2-JavaScript]
var findTargetSumWays = function(nums, target) {
    let sum = 0;
    for (const num of nums) {
        sum += num;
    }
    const diff = sum - target;
    if (diff < 0 || diff % 2 !== 0) {
        return 0;
    }
    const n = nums.length, neg = diff / 2;
    const dp = new Array(n + 1).fill(0).map(() => new Array(neg + 1).fill(0));
    dp[0][0] = 1;
    for (let i = 1; i <= n; i++) {
        const num = nums[i - 1];
        for (let j = 0; j <= neg; j++) {
            dp[i][j] = dp[i - 1][j];
            if (j >= num) {
                dp[i][j] += dp[i - 1][j - num];
            }
        }
    }
    return dp[n][neg];
};
```

```go [sol2-Golang]
func findTargetSumWays(nums []int, target int) int {
    sum := 0
    for _, v := range nums {
        sum += v
    }
    diff := sum - target
    if diff < 0 || diff%2 == 1 {
        return 0
    }
    n, neg := len(nums), diff/2
    dp := make([][]int, n+1)
    for i := range dp {
        dp[i] = make([]int, neg+1)
    }
    dp[0][0] = 1
    for i, num := range nums {
        for j := 0; j <= neg; j++ {
            dp[i+1][j] = dp[i][j]
            if j >= num {
                dp[i+1][j] += dp[i][j-num]
            }
        }
    }
    return dp[n][neg]
}
```

```C++ [sol2-C++]
class Solution {
public:
    int findTargetSumWays(vector<int>& nums, int target) {
        int sum = 0;
        for (int& num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.size(), neg = diff / 2;
        vector<vector<int>> dp(n + 1, vector<int>(neg + 1));
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];
    }
};
```

```C [sol2-C]
int findTargetSumWays(int* nums, int numsSize, int target) {
    int sum = 0;
    for (int i = 0; i < numsSize; i++) {
        sum += nums[i];
    }
    int diff = sum - target;
    if (diff < 0 || diff % 2 != 0) {
        return 0;
    }
    int n = numsSize, neg = diff / 2;
    int dp[n + 1][neg + 1];
    memset(dp, 0, sizeof(dp));
    dp[0][0] = 1;
    for (int i = 1; i <= n; i++) {
        int num = nums[i - 1];
        for (int j = 0; j <= neg; j++) {
            dp[i][j] = dp[i - 1][j];
            if (j >= num) {
                dp[i][j] += dp[i - 1][j - num];
            }
        }
    }
    return dp[n][neg];
}
```

由于 ![\textit{dp} ](./p__textit{dp}_.png)  的每一行的计算只和上一行有关，因此可以使用滚动数组的方式，去掉 ![\textit{dp} ](./p__textit{dp}_.png)  的第一个维度，将空间复杂度优化到 ![O(\textit{neg}) ](./p__O_textit{neg}__.png) 。

实现时，内层循环需采用倒序遍历的方式，这种方式保证转移来的是 ![\textit{dp}\[i-1\]\[\] ](./p__textit{dp}_i-1____.png)  中的元素值。

```Java [sol3-Java]
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int neg = diff / 2;
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = neg; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[neg];
    }
}
```

```C# [sol3-C#]
public class Solution {
    public int FindTargetSumWays(int[] nums, int target) {
        int sum = 0;
        foreach (int num in nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int neg = diff / 2;
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        foreach (int num in nums) {
            for (int j = neg; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[neg];
    }
}
```

```JavaScript [sol3-JavaScript]
var findTargetSumWays = function(nums, target) {
    let sum = 0;
    for (const num of nums) {
        sum += num;
    }
    const diff = sum - target;
    if (diff < 0 || diff % 2 !== 0) {
        return 0;
    }
    const neg = Math.floor(diff / 2);
    const dp = new Array(neg + 1).fill(0);
    dp[0] = 1;
    for (const num of nums) {
        for (let j = neg; j >= num; j--) {
            dp[j] += dp[j - num];
        }
    }
    return dp[neg];
};
```

```go [sol3-Golang]
func findTargetSumWays(nums []int, target int) int {
    sum := 0
    for _, v := range nums {
        sum += v
    }
    diff := sum - target
    if diff < 0 || diff%2 == 1 {
        return 0
    }
    neg := diff / 2
    dp := make([]int, neg+1)
    dp[0] = 1
    for _, num := range nums {
        for j := neg; j >= num; j-- {
            dp[j] += dp[j-num]
        }
    }
    return dp[neg]
}
```

```C++ [sol3-C++]
class Solution {
public:
    int findTargetSumWays(vector<int>& nums, int target) {
        int sum = 0;
        for (int& num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int neg = diff / 2;
        vector<int> dp(neg + 1);
        dp[0] = 1;
        for (int& num : nums) {
            for (int j = neg; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[neg];
    }
};
```

```C [sol3-C]
int findTargetSumWays(int* nums, int numsSize, int target) {
    int sum = 0;
    for (int i = 0; i < numsSize; i++) {
        sum += nums[i];
    }
    int diff = sum - target;
    if (diff < 0 || diff % 2 != 0) {
        return 0;
    }
    int neg = diff / 2;
    int dp[neg + 1];
    memset(dp, 0, sizeof(dp));
    dp[0] = 1;
    for (int i = 0; i < numsSize; i++) {
        int num = nums[i];
        for (int j = neg; j >= num; j--) {
            dp[j] += dp[j - num];
        }
    }
    return dp[neg];
}
```

**复杂度分析**

- 时间复杂度：![O(n\times(\textit{sum}-\textit{target})) ](./p__O_n_times__textit{sum}-textit{target}___.png) ，其中 *n* 是数组 ![\textit{nums} ](./p__textit{nums}_.png)  的长度，![\textit{sum} ](./p__textit{sum}_.png)  是数组 ![\textit{nums} ](./p__textit{nums}_.png)  的元素和，![\textit{target} ](./p__textit{target}_.png)  是目标数。动态规划有 ![(n+1)\times(\dfrac{\textit{sum}-\textit{target}}{2}+1) ](./p___n+1__times__dfrac{textit{sum}-textit{target}}{2}+1__.png)  个状态，需要计算每个状态的值。

- 空间复杂度：![O(\textit{sum}-\textit{target}) ](./p__O_textit{sum}-textit{target}__.png) ，其中 ![\textit{sum} ](./p__textit{sum}_.png)  是数组 ![\textit{nums} ](./p__textit{nums}_.png)  的元素和，![\textit{target} ](./p__textit{target}_.png)  是目标数。使用空间优化的实现，需要创建长度为 ![\dfrac{\textit{sum}-\textit{target}}{2}+1 ](./p__dfrac{textit{sum}-textit{target}}{2}+1_.png)  的数组 ![\textit{dp} ](./p__textit{dp}_.png) 。