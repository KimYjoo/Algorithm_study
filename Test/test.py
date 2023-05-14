# Disjoint set find (and collapse) 
def find(nd, djset):
    uv = nd
    while djset[uv] >= 0: uv = djset[uv]
    if djset[nd] >= 0: djset[nd] = uv
    return uv

# Disjoint set union (does not modify djset)
def union(nd1, nd2, djset):
    unionset = djset.copy()
    if unionset[nd2] < unionset[nd1]:
        nd1, nd2 = nd2, nd1

    unionset[nd1] += unionset[nd2]
    unionset[nd2] = nd1
    return unionset

# Bitmask convenience methods; uses bitmasks
# internally to represent MST edge combinations
def setbit(j, mask): return mask | (1 << j)
def isbitset(j, mask): return (mask >> j) & 1
def masktoedges(mask, sedges):
    return [sedges[i] for i in range(len(sedges)) 
            if isbitset(i, mask)]

# Upper-bound count of viable MST edge combination, i.e.
# count of edge subsequences of length: NEDGES, w/sum: WEIGHT
def count_subsequences(sedges, weight, nedges):
#{
    def count(i, target, length, cache):
        tkey = (i, target, length)
        if tkey in cache: return cache[tkey]
        if i == len(sedges) or target < sedges[i][2]: return 0
            
        cache[tkey] = (count(i+1, target, length, cache) +
            count(i+1, target - sedges[i][2], length - 1, cache) + 
            (1 if sedges[i][2] == target and length == 1 else 0))
        
        return cache[tkey]
    
    return count(0, weight, nedges, {})
#}

# Arg: n is number of nodes in graph [0, n-1]
# Arg: sedges is list of graph edges sorted by weight
# Return: list of MSTs, where each MST is a list of edges
def find_all_msts(n, sedges):
#{
    # Recursive variant of kruskal to find all MSTs
    def buildmsts(i, weight, mask, nedges, djset):
    #{
        nonlocal maxweight, msts
        if nedges == (n-1):
            msts.append(mask)
            if maxweight == float('inf'):
                print(f"MST weight: {weight}, MST edges: {n-1}, Total graph edges: {len(sedges)}")
                print(f"Upper bound numb viable MST edge combinations: {count_subsequences(sedges, weight, n-1)}\n")
                maxweight = weight
                
            return
        
        if i < len(sedges):
        #{
            u,v,wt = sedges[i]
            if weight + wt*((n-1) - nedges) <= maxweight:
            #{
                # Left recursive branch - include edge if valid
                nd1, nd2 = find(u, djset), find(v, djset)
                if nd1 != nd2: buildmsts(i+1, weight + wt,
                    setbit(i, mask), nedges+1, union(nd1, nd2, djset))
            
                # Right recursive branch - always skips edge
                buildmsts(i+1, weight, mask, nedges, djset)
            #}
        #}
    #}
        
    maxweight, msts = float('inf'), []
    djset = {i: -1 for i in range(n)}    
    buildmsts(0, 0, 0, 0, djset)    
    return [masktoedges(mask, sedges) for mask in msts]
#}