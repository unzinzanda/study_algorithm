#include <iostream>

using namespace std;

int main() {
    int num[6] = {1,1,2,2,2,8};
    int n;

    for(int i = 0; i < 6;i++) {
        cin >> n;

        cout << num[i] - n << " ";
    }
    cout << "\n";
    return 0;
}