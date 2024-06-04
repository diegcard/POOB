#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
#define X first
#define Y second
#define SZ(a) ((int)a.size())
#define ALL(v) v.begin(), v.end()
#define pb push_back


const int N = 200005;
const int INF = 1e9;
 
struct node {
    int min_dec, min_inc;
} seg[N *4];
 
int n, s;
 
void chmin(int &x, int val) {
    x = min(x, val);
}
 
void down(int l, int r, int nodoActual) {
    int mid = (l + r) / 2;
    if (seg[nodoActual].min_dec != INF) {
        chmin(seg[nodoActual * 2].min_dec, seg[nodoActual].min_dec);
        chmin(seg[nodoActual * 2 + 1].min_dec, seg[nodoActual].min_dec - (mid + 1 - l));
    }
    if (seg[nodoActual].min_inc != INF) {
        chmin(seg[nodoActual * 2].min_inc, seg[nodoActual].min_inc);
        chmin(seg[nodoActual * 2 + 1].min_inc, seg[nodoActual].min_inc + (mid + 1 - l));
    }
    seg[nodoActual].min_dec = seg[nodoActual].min_inc = INF;
}

 
void build(int l, int r, int nodoActual) {
    seg[nodoActual].min_dec = seg[nodoActual].min_inc = INF;
    if (l == r) {
        seg[nodoActual].min_dec = seg[nodoActual].min_inc = min({abs(l - s), abs(n + l - s), abs(l - n - s)});
        return;
    }
    int mid = (l + r) / 2;
    build(l, mid, nodoActual * 2);
    build(mid + 1, r, nodoActual * 2 + 1);
}

 
void modify(int L, int R, int l, int r, int nodoActual, int v, int type) {
    if (L <= l && R >= r) {
        if (type == 1)
            seg[nodoActual].min_dec = min(seg[nodoActual].min_dec, v);
        else
            seg[nodoActual].min_inc = min(seg[nodoActual].min_inc, v);
        return;
    }
    down(l, r, nodoActual);
    int mid = (l + r) / 2;
    if (L <= mid) { 
        modify(L, min(mid, R), l, mid, nodoActual * 2, v, type);
        if (type == 1)
            v -= mid + 1 - L;
        else
            v += mid + 1 - L;
    }
    if (R > mid)
        modify(max(L, mid + 1), R, mid + 1, r, nodoActual * 2 + 1, v, type);
}

 
void smodify(int x, int l, int r, int nodoActual, int v) {
    if (l == r) {
        seg[nodoActual].min_dec = seg[nodoActual].min_inc = v;
        return;
    }
    down(l, r, nodoActual);
    int mid = (l + r) / 2;
    if (x <= mid) 
        smodify(x, l, mid, nodoActual * 2, v);
    else 
        smodify(x, mid + 1, r, nodoActual * 2 + 1, v);
}
 
int query(int x, int l, int r, int nodoActual) {
    if (l == r) return min(seg[nodoActual].min_dec, seg[nodoActual].min_inc);
    down(l, r, nodoActual);
    int mid = (l + r) / 2;
    if (x <= mid) return query(x, l, mid, nodoActual * 2);
    return query(x, mid + 1, r, nodoActual * 2 + 1);
}

 
int main() {
    ios::sync_with_stdio(0), cin.tie(0);
    int m;
    cin >> n >> m >> s;
    vector<pii> vec(m);
    for (auto &[d, i] : vec)
        cin >> d >> i;
    sort(ALL(vec), greater<pii>());
    build(1, n, 1);
 
    auto update = [&](int idx, int val) {
        smodify(idx, 1, n, 1, val);
        
        modify(idx, n, 1, n, 1, val, 0);
        modify(1, idx, 1, n, 1, val + n + 1 - idx, 0);
 
        modify(1, idx, 1, n, 1, val + idx - 1, 1);
        modify(idx, n, 1, n, 1, val + n, 1);
    };
 
    for (auto [d, i] : vec) {
        int hiloInicial = i, hiloSiguiente = i % n + 1;
        int lval = min(query(hiloInicial, 1, n, 1), query(hiloSiguiente % n + 1, 1, n, 1) + 1);
        int rval = min(query(hiloSiguiente, 1, n, 1), query((hiloInicial + n - 2) % n + 1, 1, n, 1) + 1);
        update(hiloInicial, rval);
        update(hiloSiguiente, lval);
    }
 
    for (int i = 1; i <= n; ++i)
        cout << query(i, 1, n, 1) << "\n";
    
    for (int i = 0; i <= 13; ++i) {
    cout << "Index: " << i << "\n";
    cout << "Min ascending value: " << seg[i].min_inc << "\n";
    cout << "Min descending value: " << seg[i].min_dec << "\n";
    }
}