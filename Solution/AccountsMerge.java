// site: https://leetcode-cn.com/problems/accounts-merge/

// 合并账户，需要用到多个map和并查集，注意map的对应关系，以及整理出结果的过程

class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailToIndex = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        int index = 0;
        // 给每个email赋予一个id，并且绑定一个名称
        for(List<String> account : accounts){
            String name = account.get(0);
            int n = account.size();
            for(int i = 1; i < n; i++){
                String email = account.get(i);
                if(!emailToIndex.containsKey(email)){
                    emailToIndex.put(email, index);
                    index++;
                    emailToName.put(email, name);
                }
            }
        }
        // 并查集
        UnionFind unionFind = new UnionFind(index);
        // 遍历账户，对同一账户里的email进行合并
        for(List<String> account : accounts){
            String firstEmail = account.get(1);
            int firstIndex = emailToIndex.get(firstEmail);
            int n = account.size();
            for(int i = 2; i < n; i++){
                String otherEmail = account.get(i);
                int otherIndex = emailToIndex.get(otherEmail);
                // 合并
                unionFind.union(firstIndex, otherIndex);
            }
        }
        // 根据并查集找出同一个集合中的邮箱
        Map<Integer, List<String>> indexToEmail = new HashMap<>();
        for(String email : emailToIndex.keySet()){
            // 找出每个email的根节点，将同一个根的email存起来
            int rootIndex = unionFind.find(emailToIndex.get(email));
            if(!indexToEmail.containsKey(rootIndex)){
                List<String> emailSet = new ArrayList<>();
                emailSet.add(email);
                indexToEmail.put(rootIndex, emailSet);
            }
            else {
                indexToEmail.get(rootIndex).add(email);
            }
        }
        // 根据并查集和map整理出结果
        // 按照indexToEmail中的value中其中一个找出名称即可
        List<List<String>> result = new ArrayList<>();
        for(List<String> emailSet : indexToEmail.values()){
            // 同一个名称下的email要排序
            Collections.sort(emailSet);
            String name = emailToName.get(emailSet.get(0));
            List<String> account = new ArrayList<>();
            // 先加入名字，然后用addAll加入邮箱
            account.add(name);
            account.addAll(emailSet);
            result.add(account);
        }
        return result;
    }
    private class UnionFind{
        private int[] parent;
        
        public UnionFind(int n){
            parent = new int[n];
            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
        }

        public int find(int x){
            if(parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX != rootY){
                parent[rootX] = rootY;
            }
        }
    }
}
